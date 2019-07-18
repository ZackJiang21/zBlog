define(function (require, exports, module) {
    var blogObj = require('./blog').Blog;
    var handlebars = require("../../3rd/handlebars.js").Handlebars;
    var blogsTpl = require('../../view/blog/tpl/blogs.tpl');
    var blogContentTpl = require("/view/blog/tpl/blogContent.tpl");


    function refeshBlogs() {
        blogObj.getBlogs().done(function (data) {
            var blogTplC = handlebars.compile(blogsTpl);
            var handleData = {
                data: data
            };
            var blogHtml = blogTplC(handleData);
            $("#zblog-content").html(blogHtml)
            attachEvent();
        });
    }

    function attachEvent() {
        $(".blog-title").click(function () {
            var id = $(this).attr('bid');
            blogObj.getBlogById(id).done(function (data) {
                $("#zblog-content").html(blogContentTpl);
                $("#blog-content").html(data.viewContent);

                $("#backBtn").click(function () {
                    refeshBlogs();
                });
            });
        });
    }

    exports.refreshBlogs = refeshBlogs;
})