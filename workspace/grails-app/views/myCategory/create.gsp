

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create MyCategory</title>         
    </head>
    <body>
        <div class="nav">
        	<span class="menuButton"><g:link url="[action:'myTweetMarks', controller:',myTweetMark']">Profile</g:link></span>
        </div>
        <div class="body">
            <h1>Create MyCategory</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${myCategoryInstance}">
            <div class="errors">
                <g:renderErrors bean="${myCategoryInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="saveMyCategory" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                        <!-- useful for testing
                        	<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="categoryId">Category:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myCategoryInstance,field:'categoryId','errors')}">
                                    <input type="text" id="categoryId" name="categoryId" value="${fieldValue(bean:myCategoryInstance,field:'categoryId')}" />
                                </td>
                            </tr> 
                        -->	                        	
                        	<tr class="prop">
                        	<td valign="top" class="name">
                        		<label for="categoryId">Category:</label>
                        	</td>
                        	<td valign="top" class="value ${hasErrors(bean:myCategoryInstance,field:'categoryId','errors')}">
                        		<g:select optionKey="id" optionValue="name" name="categoryId" from="${Categories.list()}" value="${categoryId}"/>
          					</td>
          					</tr>
          					 
                        	<!--
                        	<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createTime">Create Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myCategoryInstance,field:'createTime','errors')}">
                                    <g:datePicker name="createTime" value="${myCategoryInstance?.createTime}" ></g:datePicker>
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
                                    <label for="updateTime">Update Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myCategoryInstance,field:'updateTime','errors')}">
                                    <g:datePicker name="updateTime" value="${myCategoryInstance?.updateTime}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="userId">User Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:myCategoryInstance,field:'id','errors')}">
                                    <input type="text" id="userId" name="userId" value="${fieldValue(bean:myCategoryInstance,field:'id')}" />
                                </td>
                            </tr> 
                        	-->
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
