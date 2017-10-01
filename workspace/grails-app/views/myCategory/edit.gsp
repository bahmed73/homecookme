

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit MyCategory</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">MyCategory List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New MyCategory</g:link></span>
        </div>
        <div class="body">
            <h1>Edit MyCategory</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${myCategoryInstance}">
            <div class="errors">
                <g:renderErrors bean="${myCategoryInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${myCategoryInstance?.id}" />
                <input type="hidden" name="version" value="${myCategoryInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="userId">User Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myCategoryInstance,field:'userId','errors')}">
                                    <input type="text" id="userId" name="userId" value="${fieldValue(bean:myCategoryInstance,field:'userId')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="categoryId">Category Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myCategoryInstance,field:'categoryId','errors')}">
                                    <input type="text" id="categoryId" name="categoryId" value="${fieldValue(bean:myCategoryInstance,field:'categoryId')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="status">Status:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myCategoryInstance,field:'status','errors')}">
                                    <input type="text" id="status" name="status" value="${fieldValue(bean:myCategoryInstance,field:'status')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createTime">Create Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myCategoryInstance,field:'createTime','errors')}">
                                    <g:datePicker name="createTime" value="${myCategoryInstance?.createTime}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="updateTime">Update Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myCategoryInstance,field:'updateTime','errors')}">
                                    <g:datePicker name="updateTime" value="${myCategoryInstance?.updateTime}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="numMyTweets">Num My Tweets:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myCategoryInstance,field:'numMyTweets','errors')}">
                                    <input type="text" id="numMyTweets" name="numMyTweets" value="${fieldValue(bean:myCategoryInstance,field:'numMyTweets')}" />
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
