<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item" aria-current="page">控制台</li>
        <li class="breadcrumb-item active" aria-current="page">分类管理</li>
    </ol>
</nav>
<div class="z-buttons">
    <button type="button" class="btn btn-primary z-button" data-toggle="modal" data-target="#addCategoryModal">创建</button>
</div>
<table class="table zblog-table">
    <thead>
    <th>名称</th>
    <th>文章数</th>
    <th>操作</th>
    </thead>
    {{#each data}}
    <tr>
        <td>{{name}}</td>
        <td>{{blogCount}}</td>

        <td>
            {{#if canDelete}}
            <a cid="{{id}}" href="javascript:void(0)" class="delCategoryBtn">删除</a>
            {{/if}}
        </td>
    </tr>
    {{/each}}
</table>
