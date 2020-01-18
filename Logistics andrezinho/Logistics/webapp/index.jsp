<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EC108 - Logistics Services</title>
</head>
<body>

<h1>EC108 - Logistics Services</h1>

<h2>SOAP Services</h2>

Web Service Definition: <a href="<%=request.getRequestURL()%>/services/LogisticsService?wsdl" target="_blank"><%=request.getRequestURL()%>/services/LogisticsService?wsdl</a>

<p>Available Operations:</p>
<ul>
<li>getDeliveries = Returns all registered Deliveries</li>
<li>getDeliveriesToDeliver = Returns Deliveries to be Deliver</li>
<li>getDeliveryById = Returns Delivery by ID</li>
<li>getDeliveryByOrderId = Returns Delivery by Sales Order ID</li>
<li>insertDelivery = Add new Delivery with the Sales Order ID and CRM Client ID</li>
<li>makeDelivery = Register Delivery Made with the delivery information (position, time, receiver, etc...) </li>
<li>deleteDelivery = Delete a Delivery from database </li>
</ul>


<h2>REST Services</h2>

<p>Edy, please don't hate my table. I'm not a Designer Guy :-)</p>
<p>There is an Administrator User registered with username: 'admin' and password: 'admin' for you to test the Services.</p>
<p>Used reference to implement Authentication and validation with @RolesAllowed annotations in the REST services: <a href=http://stackoverflow.com/questions/9591227/custom-rolesallowed-roles-in-jersey-webservice-with-containerrequestfilter target="_blank">http://stackoverflow.com/questions/9591227/custom-rolesallowed-roles-in-jersey-webservice-with-containerrequestfilter</a></p>

<table border="1" cellpadding="5%">
<tr>
	<th colspan = "6">
		User Services
	</th>
</tr>

<tr>
	<th>Service</th>
	<th>Address</th>
	<th>Method</th>
	<th>Auth Required</th>
	<th>Content Body</th>
	<th>Dependencies</th>
</tr>

<tr>
	<td>Create New User</td>
	<td><a href="<%=request.getRequestURL()%>api/user"><%=request.getRequestURL()%>api/user</a></td>
	<td>POST</td>
	<td>NO</td>
	<td>
		{<br/>
            "name": "Name Value",<br/>
            "password": "Password Value",<br/>
            "userName": "Username Value"<br/>
        }
	</td>
	<td>NONE</td>
</tr>

<tr>
	<td>Update User</td>
	<td><a href="<%=request.getRequestURL()%>api/user/1"><%=request.getRequestURL()%>api/user/1</a> (Replace 1 for the User ID)</td>
	<td>PUT</td>
	<td>YES - Role = Admin or User owner of the register</td>
	<td>
		{<br/>
            "name": "New Name Value",<br/>
            "password": "New Password Value"<br/>
        }
	</td>
	<td>NONE</td>
</tr>

<tr>
	<td>Delete User</td>
	<td><a href="<%=request.getRequestURL()%>api/user/1"><%=request.getRequestURL()%>api/user/1</a> (Replace 1 for the User ID)</td>
	<td>DELETE</td>
	<td>YES - Role = Admin or User owner of the register</td>
	<td>NONE</td>
	<td>NONE</td>
</tr>

<tr>
	<td>Get User By ID</td>
	<td><a href="<%=request.getRequestURL()%>api/user/1"><%=request.getRequestURL()%>api/user/1</a> (Replace 1 for the User ID)</td>
	<td>GET</td>
	<td>YES - Role = Admin or User owner of the register</td>
	<td>NONE</td>
	<td>NONE</td>
</tr>

<tr>
	<td>Get All Users</td>
	<td><a href="<%=request.getRequestURL()%>api/user"><%=request.getRequestURL()%>api/user</a></td>
	<td>GET</td>
	<td>YES - Role = Admin</td>
	<td>NONE</td>
	<td>NONE</td>
</tr>

<tr>
	<th colspan = "6">
		Logistics Services
	</th>
</tr>

<tr>
	<td>Get All Deliveries</td>
	<td><a href="<%=request.getRequestURL()%>api/delivery"><%=request.getRequestURL()%>api/delivery</a></td>
	<td>GET</td>
	<td>YES - Role = Admin or User</td>
	<td>NONE</td>
	<td>Sales Service And CRM Service</td>
</tr>
<tr>
	<td>Get All Deliveries To Deliver</td>
	<td><a href="<%=request.getRequestURL()%>api/delivery/toDeliver"><%=request.getRequestURL()%>api/delivery/toDeliver</a></td>
	<td>GET</td>
	<td>YES - Role = Admin or User</td>
	<td>NONE</td>
	<td>Sales Service And CRM Service</td>
</tr>
<tr>
	<td>Get Delivery By ID</td>
	<td><a href="<%=request.getRequestURL()%>api/delivery/1"><%=request.getRequestURL()%>api/delivery/1</a> (Replace 1 for the Delivery ID)</td>
	<td>GET</td>
	<td>YES - Role = Admin or User</td>
	<td>NONE</td>
	<td>Sales Service And CRM Service</td>
</tr>
<tr>
	<td>Get Delivery By Order ID</td>
	<td><a href="<%=request.getRequestURL()%>api/delivery/byOrderId/1"><%=request.getRequestURL()%>api/delivery/byOrderId/1</a> (Replace 1 for the Order ID)</td>
	<td>GET</td>
	<td>YES - Role = Admin or User</td>
	<td>NONE</td>
	<td>Sales Service And CRM Service</td>
</tr>
<tr>
	<td>Make Delivery</td>
	<td><a href="<%=request.getRequestURL()%>api/delivery/makeDelivery/1"><%=request.getRequestURL()%>api/delivery/makeDelivery/1</a> (Replace 1 for the Delivery ID)</td>
	<td>PUT</td>
	<td>YES - Role = Admin or User</td>
	<td>
		{ <br/>
		"receiverName": "NOME DO RECEBEDOR", <br/>
		"receiverCpf": "CPF_RECEB", <br/>
		"isClientReceiver": "true", <br/>
		"deliveryMadeTime": "2013-12-12T14:31:16-02:00", <br/>
		"deliveryMadeLatitude": "10.0", <br/>
		"deliveryMadeLongitude": "20.0" <br/>			
		}
	</td>
	<td>NONE</td>
</tr>
<tr>
	<td>Add Delivery</td>
	<td><a href="<%=request.getRequestURL()%>api/delivery"><%=request.getRequestURL()%>api/delivery</a></td>
	<td>POST</td>
	<td>YES - Role = Admin or User</td>
	<td>
		{ <br/>
		"orderId": "10", <br/>
		"clientId": "1" <br/>
		}
	</td>
	<td>NONE</td>
</tr>
<tr>
	<td>Delete Delivery</td>
	<td><a href="<%=request.getRequestURL()%>api/delivery/1"><%=request.getRequestURL()%>api/delivery/1</a> (Replace 1 for the Delivery ID)</td>
	<td>DELETE</td>
	<td>YES - Role = Admin or User</td>
	<td>NONE</td>
</tr>

</table>

<br/>

<h2>Database Tables</h2>
CREATE TABLE IF NOT EXISTS `delivery` (<br/>
  `id` bigint(20) NOT NULL AUTO_INCREMENT,<br/>
  `orderId` bigint(20) NOT NULL,<br/>
  `clientId` bigint(20) NOT NULL,<br/>
  `receiverName` varchar(200) DEFAULT NULL,<br/>
  `receiverCpf` varchar(12) DEFAULT NULL,<br/>
  `isClientReceiver` tinyint(1) DEFAULT NULL,<br/>
  `deliveryMadeTime` timestamp NULL DEFAULT NULL,<br/>
  `deliveryMadeLatitude` varchar(60) DEFAULT NULL,<br/>
  `deliveryMadeLongitude` varchar(60) DEFAULT NULL,<br/>
  `status` varchar(50) DEFAULT NULL,<br/>
  PRIMARY KEY (`id`)<br/>
)<br/>
<br/>
<br/>
CREATE TABLE IF NOT EXISTS `user` (<br/>
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,<br/>
  `name` varchar(200) NOT NULL DEFAULT '0',<br/>
  `username` varchar(200) NOT NULL DEFAULT '0',<br/>
  `password` varchar(200) NOT NULL DEFAULT '0',<br/>
  `role` varchar(200) NOT NULL DEFAULT '0',<br/>
  PRIMARY KEY (`id`)<br/>
)<br/>

</body>
</html>