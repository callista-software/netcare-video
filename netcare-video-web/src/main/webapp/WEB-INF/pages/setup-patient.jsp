<%--

    Copyright (C) 2011,2012 Callista Enterprise AB <info@callistaenterprise.se>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="netcare"  uri="http://www.callistasoftware.org/netcare/tags"%>
<%@ taglib prefix="mvk" uri="http://www.callistasoftware.org/mvk/tags"%>

<mvk:page>
	<mvk:header title="Netcare Video 2.0" resourcePath="/web/resources" contextPath="${pageContext.request.contextPath}">
		<netcare:js resourcePath="/web/resources" />
		<script type="text/javascript" src="<c:url value="/js/netcare-video.js" />"></script>
		<script type="text/javascript">
			$(function() {
				$('#userForm').submit(function(e) {
					e.preventDefault();
					
					var firstName = $('input[name="firstName"]').val();
					var surName = $('input[name="surName"]').val();
					
					if (firstName == "" || surName == "") {
						alert('Vänligen fyll i både för- och efternamn!');
						return false;
					}
					
					var ajax = new NC.Ajax();
					ajax.postWithParams('/user/saveUserData', { firstName : firstName, surName : surName }, function(data) {
						NC.log('Redirecting to: ' + NC.getContextPath() + '/web/dashboard');
						window.location = NC.getContextPath() + '/web/dashboard';
					}, false);
					
				});
			});
		</script>
	</mvk:header>
	<body>
		<div class="modal-backdrop fade in"></div>
		<div id="modal-from-dom" class="modal hide fade in" style="display: block;">
				<div class="modal-header">
					<h3><spring:message code="setup.header" /></h3>
					<p>
						<span class="label notice"><spring:message code="label.information" /></span>
						<spring:message code="setup.description" />
					</p>
				</div>
				
				<div class="modal-body">
					<form id="userForm" method="post" action="#" class="form-stacked">
					<fieldset>
						<legend><spring:message code="setup.title" /></legend>
					
						<div class="clearfix">
							<label for="firstName"><spring:message code="setup.firstName" /></label>
							<div class="input">
								<input name="firstName" type="text" class="xlarge" />
							</div>
						</div>
					
						<div class="clearfix">
							<label for="surName"><spring:message code="setup.surName" /></label>
							<div class="input">
								<input name="surName" type="text" class="xlarge" />
							</div>
						</div>
						
					</fieldset>
				</div>
				<div class="modal-footer">
					<input class="btn btn-info" type="submit" value="<spring:message code="setup.proceed" />" />
					</form>	
				</div>
		</div>
	</body>
</mvk:page>