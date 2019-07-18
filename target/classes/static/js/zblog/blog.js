define(function (require, exports, module) {
    function Blog() {

    }

    var BLOG_URL = "/zblog/blogs";

    Blog.getBlogs = function (succCallBack, failCallBack) {
        return $.ajax({
            type: 'GET',
            url: BLOG_URL,
            contentType: 'application/json'
        });
    };

    Blog.getBlogById = function (id) {
        return $.ajax({
            type: 'GET',
            url: BLOG_URL + '/' + id
        });
    };

    exports.Blog = Blog;
})