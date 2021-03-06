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

<%@ taglib prefix="netcare"  uri="http://www.callistasoftware.org/netcare/tags"%>
<%@ taglib prefix="mvk" uri="http://www.callistasoftware.org/mvk/tags"%>
<%@ taglib prefix="video" tagdir="/WEB-INF/tags" %>

<mvk:page>
	<video:viewHeader>
		<script type="text/javascript">
			$(function() {
				$('#modal-from-dom').modal('show');
				$('input[name="j_username"]').focus();
			});
		</script>
	</video:viewHeader>
	<body>
	
		<form method="post" action="<spring:url value="/j_spring_security_check" />">
			<netcare:modal confirmCode="login" titleCode="login" id="modal-from-dom">
				<div class="clearfix">
					<label for="j_username">Användarnamn</label>
					<div class="input">
						<input name="j_username" type="text" class="xlarge" />
					</div>
				</div>
			</netcare:modal>
		</form>
	
	</body>
</mvk:page>