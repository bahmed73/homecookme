

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create ViralSummary</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ViralSummary List</g:link></span>
        </div>
        <div class="body">
            <h1>Create ViralSummary</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${viralSummaryInstance}">
            <div class="errors">
                <g:renderErrors bean="${viralSummaryInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createTime">Create Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralSummaryInstance,field:'createTime','errors')}">
                                    <g:datePicker name="createTime" value="${viralSummaryInstance?.createTime}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalRegistrations">Total Registrations:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralSummaryInstance,field:'totalRegistrations','errors')}">
                                    <input type="text" id="totalRegistrations" name="totalRegistrations" value="${fieldValue(bean:viralSummaryInstance,field:'totalRegistrations')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalAddressBookScrapedSize">Total Address Book Scraped Size:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralSummaryInstance,field:'totalAddressBookScrapedSize','errors')}">
                                    <input type="text" id="totalAddressBookScrapedSize" name="totalAddressBookScrapedSize" value="${fieldValue(bean:viralSummaryInstance,field:'totalAddressBookScrapedSize')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalSendSize">Total Send Size:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralSummaryInstance,field:'totalSendSize','errors')}">
                                    <input type="text" id="totalSendSize" name="totalSendSize" value="${fieldValue(bean:viralSummaryInstance,field:'totalSendSize')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalOpened">Total Opened:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralSummaryInstance,field:'totalOpened','errors')}">
                                    <input type="text" id="totalOpened" name="totalOpened" value="${fieldValue(bean:viralSummaryInstance,field:'totalOpened')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalClicked">Total Clicked:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralSummaryInstance,field:'totalClicked','errors')}">
                                    <input type="text" id="totalClicked" name="totalClicked" value="${fieldValue(bean:viralSummaryInstance,field:'totalClicked')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalRegistered">Total Registered:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralSummaryInstance,field:'totalRegistered','errors')}">
                                    <input type="text" id="totalRegistered" name="totalRegistered" value="${fieldValue(bean:viralSummaryInstance,field:'totalRegistered')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="isSelfReg">Is Self Reg:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralSummaryInstance,field:'isSelfReg','errors')}">
                                    <input type="text" id="isSelfReg" name="isSelfReg" value="${fieldValue(bean:viralSummaryInstance,field:'isSelfReg')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="isDayTotal">Is Day Total:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralSummaryInstance,field:'isDayTotal','errors')}">
                                    <input type="text" id="isDayTotal" name="isDayTotal" value="${fieldValue(bean:viralSummaryInstance,field:'isDayTotal')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="marketingId">Marketing Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralSummaryInstance,field:'marketingId','errors')}">
                                    <input type="text" id="marketingId" name="marketingId" value="${fieldValue(bean:viralSummaryInstance,field:'marketingId')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="countryId">Country Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralSummaryInstance,field:'countryId','errors')}">
                                    <input type="text" id="countryId" name="countryId" value="${fieldValue(bean:viralSummaryInstance,field:'countryId')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="categoryId">Category Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralSummaryInstance,field:'categoryId','errors')}">
                                    <input type="text" id="categoryId" name="categoryId" value="${fieldValue(bean:viralSummaryInstance,field:'categoryId')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="duration">Duration:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralSummaryInstance,field:'duration','errors')}">
                                    <input type="text" id="duration" name="duration" value="${fieldValue(bean:viralSummaryInstance,field:'duration')}" />
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
