function redirectadminstratorpage(){
    swal("Admin!","Redirecting To Admin Actions Page!","success")
    setTimeout(function(){
        window.location="adminaction.jsp";
    },3000);
}

function redirectvotingpage(){
    swal("Admin!","Redirecting to Voting Controller Page!","success")
    setTimeout(function(){
        window.location="VotingControllerServlet";
    },3000);
}

function manageuser(){
    swal("Admin!","Redirecting to User Management Page!","success")
    setTimeout(function(){
        window.location="manageuser.jsp";
    },3000);
}

function managecandidate(){
    swal("Admin!","Redirecting To Candidate Management Page!","success")
    setTimeout(function(){
        window.location="managecandidate.jsp";
    },3000);
}

function showaddcandidateform()
{
    removecandidateForm();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red");
    newdiv.innerHTML="<h3>Add New Candidate</h3>";
    newdiv.innerHTML=newdiv.innerHTML+
            "<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>\n\
             <table><tr><th>Candidate Id:</th><td><input type='text' id='cid'></td></tr>\n\
             <tr><th>User Id:</th><td><input type='text' id='uid' onkeypress='return getdetails(event)'></td></tr>\n\
             <tr><th>Candidate Name:</th><td><input type='text' id='cname'></td></tr>\n\
             <tr><th>City:</th><td><select id='city'></select></td></tr>\n\
             <tr><th>Party:</th><td><input type='text' id='party'></td></tr>\n\
             <tr><td colspan='2'><input type='file' name='files' value='Select Image'></td></tr>\n\
             <tr><th><input type='button' value='Add Candidate' onclick='addcandidate()' id='addcnd'></th>\n\
             <th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";
    newdiv.innerHTML=newdiv.innerHTML+
             "<br><span id='addresp'></span>";
     var addcand=$("#result")[0];
     addcand.appendChild(newdiv);
     $("#candidateform").hide();
     $("#candidateform").fadeIn("3500");
     data={id:"getid"};
     $.post("AddCandidateControllerServlet",data,function(responseText){$("#cid").val(responseText);$('#cid').prop("disabled",true)});
}

function addcandidate()
{
    var form=$('#fileUploadForm')[0];
    var data=new FormData(form);
    alert(data);
    var cid=$("#cid").val();
    var cname=$("#cname").val();
    var city=$("#city").val();
    var party=$("#party").val();
    var uid=$("#uid").val();
    data.append("cid",cid);
    data.append("uid",uid);
    data.append("cname",cname);
    data.append("party",party);
    data.append("city",city);
    $.ajax({
        type:"POST", enctype:'multipart/form-data',url:"AddNewCandidateControllerServlet", data:data, processData:false, contentType:false, cache:false,
        timeout:600000,
        success:function(data){
            str=data+"....";
            swal("Admin!",str,"success").then((value)=>{
                showaddcandidateform();
            });
        }, error:function(e){
            swal("Admin!",e,"error");
        }
    });
}

function getdetails(e)
{
    if(e.keyCode===13){
        data={uid:$("#uid").val()};
        $.post("AddCandidateControllerServlet",data,function(responseText){
            let details=JSON.parse(responseText);
            let city=details.city;
            let uname=details.username;
            if(uname==="wrong")
                swal("Wrong Adhar!","Adhar no not found in DB","error");
            else
            {
                $('#city').empty();
                $('#city').append(city);
                $('#cname').val(uname);
                $('#cname').prop("disabled",true);
            }
        });
    }
}
function removecandidateForm()
{
    var contdiv=document.getElementById("result");
    var formdiv=document.getElementById("candidateform");
    if(formdiv!==null)
    {
        $("#candidateform").fadeOut("3500");
        contdiv.removeChild(formdiv);
    }
}

function clearText()
{
    $("#addresp").html("");
}

function showcandidate()
{
    removecandidateForm();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red");
    newdiv.innerHTML="<h3>Show Candidate</h3>";
    newdiv.innerHTML=newdiv.innerHTML+
           "<div style='color:white; font-weight:bold'>Candidate Id:</div><div><select id='cid'></select></div>";
    newdiv.innerHTML=newdiv.innerHTML+
             "<br><span id='addresp'></span>";
     var addPrd=$("#result")[0];
     addPrd.appendChild(newdiv);
     $("#candidateform").hide();
     $("#candidateform").fadeIn("3500");
     data={data:"cid"};
     $.post("ShowCandidateControllerServlet",data,function(responseText){
         var cidlist=JSON.parse(responseText);
         $('#cid').append(cidlist.cids);
     });
     
     $("#cid").change(function()
     {
         var cid=$("#cid").val();
         alert("Selected id:"+cid);
         if(cid==="")
         {
             swal("No selection!","Please select an id","error");
             return;
         }
         data={data:cid};
         $.post("ShowCandidateControllerServlet",data,function(responseText)
         {
             clearText();
             var details=JSON.parse(responseText);
             $("#addresp").append(details.subdetails);
         });
     });
 }
 
 
 function votingresult()
 {
      swal("Admin!","Redirecting To Result Page!","success")
    setTimeout(function(){
        window.location="Result.jsp";
    },3000);
 }
 
 function deletecandidateform()
{
    removecandidateForm();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red");
    newdiv.innerHTML="<h3>Remove Candidate</h3>";
    newdiv.innerHTML=newdiv.innerHTML+"<div style=';color:white;font-weight:bold'>Candidate Id:</div><div><select id='cid'></select></div>";
    newdiv.innerHTML=newdiv.innerHTML+"<br><span id='addresp'></span>";
    var addcand=$("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");
    data={data:"cid"};    
    $.post("RemoveCandidateServlet",data,function(responseText){
        $('#cid').empty();
        var candidateIdList=JSON.parse(responseText);
        $('#cid').append(candidateIdList.cid);
    });
    $('#cid').change(function(){
   var cid=$('#cid').val();
   if(cid===' '){
      swal("No Selection!","Please select an Id","error");
      clearText();
       return;
   }
   else
   {
     data={data:cid};    
    $.post("RemoveCandidateServlet",data,function(responseText){
     clearText();
     var details=JSON.parse(responseText);
     $('#addresp').append(details.subdetails);
        });   
   }
});
}

function deletecandidate()
{
    var cid=$("#cid").val();
    data={cnfrm:cid};
    $.post("DeleteCandidateButtonHandlerServlet",data,function(responseText){
     var details=JSON.parse(responseText);
    if(details.status===true)
    {
     clearText();
     swal("Deletion Done","Successfully!!" , "success").then(value=>{
         window.location="managecandidate.jsp";
    });  
      }
    else
    {
       swal("Something went wrong!!","Record not deleted","error"); 
    }
});
}

function showupdatecandidateform(){
     removecandidateForm();
    //swal("sucess","form swaing","success")
   console.log("show candidate form start");
   var newdiv=document.createElement("div");
   newdiv.setAttribute("id","candidateform");
   newdiv.setAttribute("float","left");
   newdiv.setAttribute("padding-left","12px");
   newdiv.setAttribute("border","solid 2px red");
   newdiv.innerHTML="<h3>Update Candidate</h3>";
   newdiv.innerHTML=newdiv.innerHTML+"<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>>\n\
<table><tr><th>City:</th><td><select id='cid' onchange='show()'></select></td></tr>\n\
<tr><th><label for='name'>UserName</label></th><td><input type='text' id='name'></td></tr>\n\
<tr><th><label for='party'>party  </label></th><td><input type='text' id='party'></td></tr>\n\
<tr><th><label for='city'>city  </label></th><td><input type='text' id='city'></td></tr>\n\
<tr><th><label for='image'>image </label></th><td><div id='image' style='width:300px;height:200px;border:solid 2px red'></div>\n\
<tr><td colspan='2'><input type='file' name='files' value='Select Image'></td></tr>\n\
<tr><th><input type='button' value='Update Candidate' onclick='updateCandidate()' id='addcnd'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";
newdiv.innerHTML=newdiv.innerHTML+"<br><span id='addresp'></span>";
console.log("show candidate form end");
var addcand=$("#result")[0];
addcand.appendChild(newdiv);
$("#candidateform").hide();
$("#candidateform").fadeIn("3500");
data={id:"cid"};
$.post("UpdateCandidateServlet",data,function(responseText){
    console.log("response text is "+responseText)
    var details=JSON.parse(responseText);
            let cid=details.cid;
            console.log(cid);
            let id=$("#cid");
            console.log("candidate id"+id);
            $("#cid").append(cid);
             console.log("before "+id);
});
}

function showAllCandidate(){
    swal("success","all candidtes ..","success");
}
function show(){
let select=$("#cid").val();
console.log("value of selected item "+select)
if(select=='select value'){
    swal("Error","plzz select candidate id","error");
    return;
}
data={id:select};
$.post("UpdateCandidateServlet",data,function(responseText){
    //console.log("responseText in show "+responseText);
     var details=JSON.parse(responseText);
     var name=details.name;
     var city=details.city;
     var party=details.party;
     var src=details.src;
     console.log("name is"+name);
     console.log(""+city);
     console.log(party);
     //console.log(src);
    var username=document.getElementById("name");
    var usercity=document.getElementById("city");
    var userparty=document.getElementById("party");
    var img=document.getElementById("image");
    username.value=name;
    usercity.value=city
    userparty.value=party;
    img.innerHTML=src;
   
});

}

function updateCandidate(){
 var form = $('#fileUploadForm')[0];
    var data = new FormData(form);
    var cid=$("#cid").val();
    var cname=$("#name").val();
    var city=$("#city").val();
    var party=$("#party").val();
    data.append("cid",cid);
    data.append("cname",cname);
    data.append("party",party);
    data.append("city",city);
    $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "UpdateCandidateControllerServlet",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                str=data.trim();
                console.log("responseText in added "+str);
                if(str=="success"){
                swal("Admin!","candidate Updated sucessfully", "success").then((value)=>{
               showupdatecandidateform();
    })}
else{
    swal("Admin!","Candidate cannot elect from selected party in selected city", "error")
}
    },
error: function (e) {
swal("Admin!", e, "success");
}
           
    });
    }

function showuserstable()
{
    removecandidateForm();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red");
    newdiv.innerHTML=newdiv.innerHTML+"<div id='tableresp'></div>";
    var addcand=$("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");
    $.post("UserDetailsServlet",function(responseText){
    $('#tableresp').append(responseText);
});
}

function removeuserform()
{
    removecandidateForm();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red");
    newdiv.innerHTML="<h3>Remove User</h3>";
    newdiv.innerHTML=newdiv.innerHTML+"<div style=';color:white;font-weight:bold'>User Id:</div><div><select id='uid'></select></div>";
    newdiv.innerHTML=newdiv.innerHTML+"<br><span id='addresp'></span>";
    var addcand=$("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");
    data={data:"uid"};    
    $.post("RemoveUserServlet",data,function(responseText){
        $('#uid').empty();
        var userIdList=JSON.parse(responseText);
        $('#uid').append(userIdList.uid);
    });
    $('#uid').change(function(){
   var uid=$('#uid').val();
   if(uid===' '){
      swal("No Selection!","Please select an Id","error");
      clearText();
       return;
   }
   else
   {
     data={data:uid};    
    $.post("RemoveUserServlet",data,function(responseText){
     clearText();
     var details=JSON.parse(responseText);
     $('#addresp').append(details.subdetails);
        });   
   }
});
}

function deleteuser()
{
    var uid=$("#uid").val();
    data={cnfrm:uid};
    $.post("RemoveUserButtonHandlerServlet",data,function(responseText){
     var details=JSON.parse(responseText);
    if(details.status===true)
    {
     clearText();
     swal("Deletion Done","Successfully!!" , "success").then(value=>{
         window.location="manageuser.jsp";
    });  
    }
    else
    {
       swal("Something went wrong!!","Record not deleted","error"); 
    }
});
}

function showCityWise(){
     
    console.log("result showing")
    $.post("ElectionCandidateResult",function(responseText){
       var addresult=document.getElementById("data");
    console.log("add candidate"+addresult);
    addresult.innerHTML="";
    addresult.innerHTML=addresult.innerHTML+responseText;
    });
}
function showPartyWise(){
    console.log("result showing party wise")
    $.post("ElectionResult",function(responseText){
       var addresult=document.getElementById("data");
    console.log("add candidate"+addresult);
    addresult.innerHTML="";
    addresult.innerHTML=addresult.innerHTML+responseText;
    });
}
function showGenderWise(){
    console.log("result showing party wise")
    $.post("ElectionResultByGender",function(responseText){
       var addresult=document.getElementById("data");
    console.log("add candidate"+addresult);
    addresult.innerHTML="";
    addresult.innerHTML=addresult.innerHTML+responseText;
    });
}





