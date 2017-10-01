

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create MyTweetMark</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link action="myTweetMarks">Profile</g:link></span>
        </div>
        <div class="body">
            <h1>Create MyTweetMark</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${myTweetMarkInstance}">
            <div class="errors">
                <g:renderErrors bean="${myTweetMarkInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="saveMyTweetMark" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                        <input type="hidden" id="categoryId" name="categoryId" value="${fieldValue(bean:myTweetMarkInstance,field:'categoryId')}" />
                        <!--
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="categoryId">Category Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myTweetMarkInstance,field:'categoryId','errors')}">
                                    <input type="text" id="categoryId" name="categoryId" value="${fieldValue(bean:myTweetMarkInstance,field:'categoryId')}" />
                                </td>
                            </tr> 
                        -->
                        
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
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
