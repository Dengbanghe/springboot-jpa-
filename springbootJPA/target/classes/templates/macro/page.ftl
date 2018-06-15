<#macro showPage start end curPage url>
    <#list start..end as page>
        <#if curPage==page>
            <li class="disabled">
              <span style="background-color: #DEDECE">${page}</span>
            </li>
        <#else>
            <li>
                <a href="${url}${page}">${page}</a>
            </li>
        </#if>
    </#list>
</#macro>

<#--
    url:user?current=
    paginationSize: 分页组件显示大小（pagination默认，pagination-sm小，pagination-lg大）
-->
<#macro pager url totalPage curPage=1 paginationSize="" class="" showPageNum=15>
    <#local halfPage=(showPageNum/2)?int/>
    <nav aria-label="Page navigation" class="${class}">
        <ul class="pagination ${paginationSize}">
            <#if curPage gt 1>
                <li>
                    <a href="${url}${curPage+1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            <#else>
                <li class="disabled">
                  <span >
                    <span aria-hidden="true">&laquo;</span>
                  </span>
                </li>
            </#if>

            <#if (halfPage>=curPage)>
                <@showPage start=1 end=curPage  url=url  curPage=curPage/>
                <@showPage start=curPage+1 end=showPageNum  curPage=curPage url=url/>
            <#else>
                <@showPage start=curPage-halfPage end=curPage  url=url  curPage=curPage/>
                <#if (curPage+halfPage>totalPage)>
                    <#local endPage=totalPage/>
                <#else>
                    <#local endPage=curPage+halfPage/>
                </#if>
                <@showPage start=curPage+1 end=endPage  url=url  curPage=curPage/>
            </#if>

            <#if curPage lt totalPage>
                <li>
                    <a href="${url}${curPage+1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            <#else>
                <li class="disabled">
                  <span>
                    <span aria-hidden="true">&raquo;</span>
                  </span>
                </li>
            </#if>
        </ul>
    </nav>
</#macro>

