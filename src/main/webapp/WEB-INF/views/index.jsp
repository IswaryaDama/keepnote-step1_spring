
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KeepNote</title>
</head>
<body>

<form action="saveNote" method="post">
<table>
<tr><td>Note ID</td><td><input type="text" name="noteId"></td></tr>
<tr><td>Title</td><td><input type="text" name="noteTitle"></td></tr>
<tr><td>Content</td><td><input type="text" name="noteContent"></td></tr>
<tr><td>Status</td><td><input type="text" name="noteStatus"></td></tr>
<tr><td><input type="submit" value="Send"></td></tr>
</table>
</form>
<table>

<c:forEach items="${notelist}" var="note">
<tr><td>${note.noteId}</td><td>${note.noteTitle}</td><td>${note.noteContent}</td><td>${note.noteStatus}</td><td>${note.createdAt}</td><td><a href="deleteNote?noteId=${note.noteId}">DELETE</a></td><td><a href="searchNote/${note.noteId}">UPDATE</a></td></tr> 
</c:forEach>
</table>

	<!-- Create a form which will have text boxes for Note ID, title, content and status along with a Send 
		 button. Handle errors like empty fields -->

	<!-- display all existing notes in a tabular structure with Id, Title,Content,Status, Created Date and Action -->
</body>
</html>