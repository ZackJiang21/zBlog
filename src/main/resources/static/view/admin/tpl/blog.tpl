<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item" aria-current="page">控制台</li>
        <li class="breadcrumb-item active" aria-current="page">博客管理</li>
    </ol>
</nav>
<div class="z-buttons">
    <button type="button" class="btn btn-primary z-button" id="writeBlogBtn">创建</button>
</div>
<table class="table zblog-table">
    <thead>
    <th>标题</th>
    <th>分类</th>
    <th>更新时间</th>
    <th>操作</th>
    </thead>
    {{#each data}}
    <tr>
        <td>{{title}}</td>
        <td>{{category}}</td>
        <td>{{updateTime}}</td>
        <td>
            <a bid="{{id}}" href="javascript:void(0)" class="delBlogBtn">删除</a>
            <a bid="{{id}}" href="javascript:void(0)" class="modBlogBtn">修改</a>
        </td>
    </tr>
    {{/each}}
</table>