define(function (require, exports, module) {
    require("../../3rd/sweetalert.min.js");
    var handlebars = require("../../3rd/handlebars.js").Handlebars;
    var categoryTpl = require('../../view/admin/tpl/category.tpl');

    
    var CATEGORY_URL = "/zblog/categories";

    function Category(data) {
        this.id = data.id;
        this.name = data.name;
        this.blogCount =  data.blogCount;
    }

    Category.prototype.addCategory = function (succCallback, errorCallback) {
        $.ajax({
            type: 'POST',
            url: CATEGORY_URL,
            data: JSON.stringify({
                name: this.name
            }),
            contentType: 'application/json',
            success: succCallback,
            error: errorCallback
        });
    };

    Category.prototype.delCategory = function (succCallBack, errorCallback) {
        $.ajax({
            type: 'DELETE',
            url: CATEGORY_URL + '/' + this.id,
            success: succCallBack,
            error: errorCallback
        });
    };
    
    Category.getCategories = function (succCallback, errorCallback) {
        $.ajax({
            type: 'GET',
            url: CATEGORY_URL,
            contentType: 'application/json',
            success: succCallback,
            error: errorCallback
        });
    };

    function attachEvent() {
        $("#addCategoryModal").on("hidden.bs.modal", function () {
            $("#addCategoryModal #categoryName").val("");
        });
        
        $(".delCategoryBtn").click(function () {
            var id = $(this).attr('cid');
            var name = $(this).parents().children("td").eq(0).text();
            var data = {};
            data.id = id;
            var category = new Category(data);
            swal({
                title: "确定删除'"+name+"'？",
                icon: "warning",
                buttons: true
            }).then((willDelete) => {
                if (willDelete) {
                    category.delCategory(refreshTable, alertError);
                }
            });

        });
        
    };
    
    function attachEventOne() {
        $("#addCategoryBtn").click(function () {
            var name = $("#categoryName").val();
            if (!name || (name && $.trim(name) == '')) {
                sweetAlert("错误", "名称不能为空","error");
                return;
            }
            var data = {};
            data.name = name;
            var category = new Category(data);
            category.addCategory(function () {
                $("#addCategoryModal").modal("hide");
                refreshTable();
            }, alertError);
        });
    }
    
    function alertError() {
        sweetAlert("哎呦……", "出错了！","error");
    };
    
    function refreshTable() {
        Category.getCategories(function (data) {
            var categroyTplC = handlebars.compile(categoryTpl);
            var handleData = {};
            for (var i = 0; i < data.length; i++) {
                data[i].canDelete = data[i].blogCount == 0;
            }
            handleData.data = data;
            var categoryHtml = categroyTplC(handleData);
            $("#console").html(categoryHtml);
            attachEvent();
        }, alertError);
    }
    
    exports.refreshCategoryTpl = refreshTable;
    
    exports.attachEventOne = attachEventOne;

    exports.Category = Category;
});