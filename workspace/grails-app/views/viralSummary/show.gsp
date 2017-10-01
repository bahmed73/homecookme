

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show ViralSummary</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ViralSummary List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ViralSummary</g:link></span>
        </div>
        <div class="body">
            <h1>Show ViralSummary</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralSummaryInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Create Time:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralSummaryInstance, field:'createTime')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Registrations:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralSummaryInstance, field:'totalRegistrations')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Address Book Scraped Size:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralSummaryInstance, field:'totalAddressBookScrapedSize')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Send Size:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralSummaryInstance, field:'totalSendSize')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Opened:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralSummaryInstance, field:'totalOpened')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Clicked:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralSummaryInstance, field:'totalClicked')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Registered:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralSummaryInstance, field:'totalRegistered')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Is Self Reg:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralSummaryInstance, field:'isSelfReg')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Is Day Total:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralSummaryInstance, field:'isDayTotal')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Marketing Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralSummaryInstance, field:'marketingId')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Country Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralSummaryInstance, field:'countryId')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Category Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralSummaryInstance, field:'categoryId')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Duration:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralSummaryInstance, field:'duration')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${viralSummaryInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
