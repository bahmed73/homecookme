

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Comment</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link action="myTweetMarks">Profile</g:link></span>
        </div>
        <div class="body">
            <h1>Create Comment</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${commentInstance}">
            <div class="errors">
                <g:renderErrors bean="${commentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="saveComment" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                        <input type="hidden" id="contentId" name="contentId" value="${fieldValue(bean:commentInstance,field:'contentId')}" />
                        <input type="hidden" id="contentType" name="contentType" value="Post" />
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Comment:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:commentInstance,field:'description','errors')}">
                                    <input type="text" id="description" name="description" value="${fieldValue(bean:commentInstance,field:'description')}" />
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
