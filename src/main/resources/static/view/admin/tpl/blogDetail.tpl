<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item" aria-current="page">控制台</li>
        <li class="breadcrumb-item active" aria-current="page">博客管理</li>
    </ol>
</nav>
<div class="z-buttons">
    <button type="button" class="btn z-button" id="backBtn">返回</button>
</div>
<div class="card">
    <div class="card-header">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2">
                    <select class="form-control" id="categories">
                        {{#each data}}
                        <option cid="{{id}}">{{name}}</option>
                        {{/each}}
                    </select>
                </div>
                <div class="col-md-8">
                    <div class="form-group">
                        <input type="text" class="form-control" id="title" placeholder="标题" autocomplete="off">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="editor">

        {{#if isModify}}
        <textarea style="display: none" id="content" name="content">{{content}}</textarea>
        {{else}}
        <textarea style="display: none" id="content" name="content">#请输入文章内容...</textarea>
        {{/if}}
    </div>
    <div class="card-footer">
        {{#if isModify}}
        <button class="btn btn-primary z-button" id="modBlogBtn">修改</button>
        {{else}}
        <button class="btn btn-primary z-button" id="publishBtn">发表</button>
        {{/if}}
    </div>
</div>
