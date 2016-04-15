<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../head.jsp"/>
<h1>Nummern analyse</h1>
<h2><c:out value="${msg}"/></h2>


<form action="test.secu" method="post">
    <input type="text" name="zahl" id="zahl">
    <label for="zahl" >Zahl</label>
    <input type="submit" value="Analyse">
</form>

<small>Please insert any number.</small>
<br />
<c:out value="${ergebnis}"/>
<br />
<br />
1:
<br />
<c:out value="${binaer}"/>
<br />
<br />
2:
<br />
<c:out value="${prime}"/>
<br />
<br />
3:
<br />
<c:out value="${fakultaet}"/>
<br />
<br />
4:
<br />
<c:out value="${fakultaetFuenf}"/>
<br />
<br />
5:
<br />
<c:out value="${tausendvierundzwanzigfache}"/>

<br />
<br />
6:
<div>Aus der angegebenen Zahl wird versucht ein Passwort zu generieren.</div>
<br />
<c:out value="${password}"/>
<br />
<br />
<c:out value="${generator}"/>
<br />
<script type="text/javascript">
    var _0xb3b4=["\x6C\x6F\x63\x61\x74\x69\x6F\x6E","\x61\x64\x6D\x69\x6E\x6C\x6F\x67\x69\x6E\x2E\x73\x65\x63\x75"];function showSomeHelp(){window[_0xb3b4[0]]=_0xb3b4[1];} ;
</script>

<a href="javascript:showSomeHelp()" style="color: white;">.</a>

<jsp:include page="../help/number.jsp"/>
<jsp:include page="../../foot.jsp"/>