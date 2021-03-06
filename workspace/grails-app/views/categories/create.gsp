

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Categories</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Categories List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Categories</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${categoriesInstance}">
            <div class="errors">
                <g:renderErrors bean="${categoriesInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:categoriesInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:categoriesInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="status">Status:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:categoriesInstance,field:'status','errors')}">
                                    <input type="text" id="status" name="status" value="${fieldValue(bean:categoriesInstance,field:'status')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createTime">Create Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:categoriesInstance,field:'createTime','errors')}">
                                    <g:datePicker name="createTime" value="${categoriesInstance?.createTime}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="orderBy">Order By:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:categoriesInstance,field:'orderBy','errors')}">
                                    <input type="text" id="orderBy" name="orderBy" value="${fieldValue(bean:categoriesInstance,field:'orderBy')}" />
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
