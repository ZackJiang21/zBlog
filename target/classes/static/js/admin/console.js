define(function (require, exports, module){
    var handlebars = require("../../3rd/handlebars.js").Handlebars;
    var categoryModelTpl = require('../../view/admin/tpl/categoryModel.tpl');
    
    var categroyCtrl = require('./category.js');
    var blogCtrl =  require('./blog.js');
    
    var MENU_ID = {
    	CATEGORY: "category-menu",
    	BLOG: "blog-menu"
    };

    function init() {
        refreshCategory();

        $(".zblog-menu").click(function () {
            $(".zblog-menu").removeClass("active");
            $(this).addClass("active");
            var menuId = $(this).attr("mid");
            if (MENU_ID.BLOG == menuId) {
                refreshBlogs();

            } else if (MENU_ID.CATEGORY == menuId) {
                refreshCategory();
            }
        })
    }
    
    function refreshCategory() {
    	categroyCtrl.refreshCategoryTpl();
        $("#extend").html(categoryModelTpl);
        categroyCtrl.attachEventOne();
    }
    
    function refreshBlogs() {
    	blogCtrl.refreshBLogsTpl();
        $("#extend").html('');
    }
    
    exports.init = init;

});

