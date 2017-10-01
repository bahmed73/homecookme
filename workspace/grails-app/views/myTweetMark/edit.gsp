

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit MyTweetMark</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">MyTweetMark List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New MyTweetMark</g:link></span>
        </div>
        <div class="body">
            <h1>Edit MyTweetMark</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${myTweetMarkInstance}">
            <div class="errors">
                <g:renderErrors bean="${myTweetMarkInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${myTweetMarkInstance?.id}" />
                <input type="hidden" name="version" value="${myTweetMarkInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="categoryId">Category Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myTweetMarkInstance,field:'categoryId','errors')}">
                                    <input type="text" id="categoryId" name="categoryId" value="${fieldValue(bean:myTweetMarkInstance,field:'categoryId')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="userId">User Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myTweetMarkInstance,field:'userId','errors')}">
                                    <input type="text" id="userId" name="userId" value="${fieldValue(bean:myTweetMarkInstance,field:'userId')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="url">Url:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myTweetMarkInstance,field:'url','errors')}">
                                    <input type="text" id="url" name="url" value="${fieldValue(bean:myTweetMarkInstance,field:'url')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="status">Status:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myTweetMarkInstance,field:'status','errors')}">
                                    <input type="text" id="status" name="status" value="${fieldValue(bean:myTweetMarkInstance,field:'status')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createTime">Create Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myTweetMarkInstance,field:'createTime','errors')}">
                                    <g:datePicker name="createTime" value="${myTweetMarkInstance?.createTime}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="updateTime">Update Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myTweetMarkInstance,field:'updateTime','errors')}">
                                    <g:datePicker name="updateTime" value="${myTweetMarkInstance?.updateTime}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myTweetMarkInstance,field:'description','errors')}">
                                    <input type="text" id="description" name="description" value="${fieldValue(bean:myTweetMarkInstance,field:'description')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
