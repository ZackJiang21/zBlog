define(function (require, exports, module) {
    var blogTpl = require('../../view/admin/tpl/blog.tpl');
    var blogDetailTpl = require('../../view/admin/tpl/blogDetail.tpl');
    var category = require('./category.js').Category;
    var handlebars = require("../../3rd/handlebars.js").Handlebars;
    var BLOG_URL = "/zblog/blogs";

    var editor;
    
    function Blog(data) {
        this.id = data.id;
        this.title = data.title;
        this.category = data.category;
        this.updateTime = data.updateTime;
        this.content = data.content;
        this.viewContent = data.viewContent;
    }

    Blog.prototype.addBlog = function (succCallback, failCallback) {
        $.ajax({
            type: 'POST',
            url: BLOG_URL,
            contentType: 'application/json',
            data: JSON.stringify({
                title: this.title,
                category: this.category,
                content: this.content,
                viewContent: this.viewContent
            }),
            success: succCallback,
            error: failCallback
        });
    };
    
    Blog.prototype.delBlog = function (succCallBack, failCallback) {
        $.ajax({
            type: 'DELETE',
            url: BLOG_URL + '/' + this.id,
            success: succCallBack,
            error: failCallback
        });
    };

    Blog.prototype.modifyBlog = function (succCallBack, failCallback) {
      $.ajax({
          type: 'PUT',
          url: BLOG_URL + '/' + this.id,
          contentType: 'application/json',
          data: JSON.stringify({
              title: this.title,
              category: this.category,
              content: this.content,
              viewContent: this.viewContent
          }),
          success: succCallBack,
          error: failCallback
      })
    };

    Blog.prototype.getBlogById = function (succCallback, failCallback) {
      $.ajax({
          type: 'GET',
          url: BLOG_URL + '/' + this.id,
          success: succCallback,
          error: failCallback
      })
    };

    Blog.getBlogBases = function (succCallback, failCallback) {
        $.ajax({
            type: 'GET',
            url: BLOG_URL,
            contentType: 'application/json',
            success: succCallback,
            error: failCallback
        });
    };

    function alertError() {
        sweetAlert("哎呦……", "出错了！","error");
    };

    function refreshBlogs() {
        Blog.getBlogBases(function (data) {
            var blogTplC = handlebars.compile(blogTpl);
            var handleData = {};
            handleData.data = data;
            var blogHtml = blogTplC(handleData);
            $("#console").html(blogHtml);
            attachEvent();
         }, alertError)
    }
    
    function attachEvent() {
        $('#writeBlogBtn').click(getWriteBlogPage);
        
        $('.delBlogBtn').click(function () {
            var blog = new Blog({});
            blog.id = $(this).attr('bid');
            var title = $(this).parents().children("td").eq(0).text();
            swal({
                title: "确定删除'"+title+"'？",
                icon: "warning",
                buttons: true
            }).then((willDelete) => {
                if (willDelete) {
                    blog.delBlog(refreshBlogs, alertError);
                }
            });
        });

        $('.modBlogBtn').click(function () {
            var blogId = $(this).attr('bid');
            getModBlogPage(blogId);
        });
    }

    function getModBlogPage(id) {
        var blogData = {};
        blogData.id = id;

        var blog = new Blog(blogData);
        category.getCategories(function (categoryData) {
            blog.getBlogById(function (blogData) {
                var handleData = {};
                handleData.data = categoryData;
                handleData.isModify = true;
                handleData.content = blogData.content;
                initEditor(handleData);
                $('#title').val(blogData.title);
                $('#categories').find('option[cid='+ blogData.category +']').attr('selected', true);
                attachModifyEvent(id);
            }, alertError);
        }, alertError);
    }
    
    function getWriteBlogPage() {
        category.getCategories(function (data) {
            var handleData = {};
            handleData.data = data;
            initEditor(handleData);
            
        }, alertError);
    }

    function initEditor(handleData) {
        var wblogTplC = handlebars.compile(blogDetailTpl);
        var wblogHtml = wblogTplC(handleData);
        $('#console').html(wblogHtml);
        attachWBlogEvent();
        editor = editormd({
            id: "editor",
            width: "100%",
            height: 500,
            syncScrolling: "single",
            path: "../../3rd/editormd/lib/",

            // 开启上传图片功能
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL: "/upload/image",

            emoji: false,                 // Emoji表情
            saveHTMLToTextarea: true,    // 保存 HTML 到 Textarea
            searchReplace: true,
            flowChart: false,             // 开启流程图支持，默认关闭
            sequenceDiagram: false,       // 开启时序/序列图支持，默认关闭,
        });
    }
    
    function attachWBlogEvent() {
        $('#backBtn').click(function () {
            refreshBlogs();
        });
        
        $('#publishBtn').click(function () {
            var blogData = getBlogData();

            
            var blog = new Blog(blogData);
            blog.addBlog(refreshBlogs, alertError);
        });
    }
    
    function attachModifyEvent(id) {
        $('#modBlogBtn').click(function () {
            var blogData = getBlogData();
            blogData.id = id;
            var blog = new Blog(blogData)
            blog.modifyBlog(function () {
                refreshBlogs();
            }, alertError)
        });
    }

    function getBlogData() {
        var blogData = {};
        blogData.title = $('#title').val();
        blogData.category = $('#categories option:selected').attr('cid');
        blogData.content = editor.getMarkdown();
        blogData.viewContent = editor.getHTML();
        return blogData;
    }
    
    exports.refreshBLogsTpl = refreshBlogs;
});