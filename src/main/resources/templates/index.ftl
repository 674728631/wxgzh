<!DOCTYPE html>
<html lang="en">
<head>
    <title>SpringBoot + Freemarker</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
    <H1>Hello ${nickname}</H1>


    <img src="${headimgurl}">

    <H2>昵称：  ${nickname}</H2>
    <H2>性别： ${sex}==1?男:女</H2>
    <H2>国家：  ${country}</H2>
    <H2>城市：${province} ${city}</H2>

</body>
</html>
