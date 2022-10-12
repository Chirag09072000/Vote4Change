let username,password,cpassword,city,address,adhar,email,mobile,gender;
function addUser()
{
    username=$("#username").val();
    password=$("#password").val();
    cpassword=$("#cpassword").val();
    city=$("#city").val();
    address=$("#address").val();
    adhar=$("#adhar").val();
    email=$("#email").val();
    mobile=$("#mobile").val();
    gender=$('input[type=radio][name=gender]:checked').val();
    if (validateUser())
    {
        if(password!==cpassword)
        {
            swal("Error!","Passwords do not match!","error");
            return;
        }
        else
        {
            if((checkEmail()===false)|| (checkMobileNo()===false) || (validateAdharNo()===false))
                    return;
                    
            let data={username:username,
                      password:password,
                      city:city,
                      address:address,
                      userid:adhar,
                      email:email,
                      mobile:mobile,
                      gender:gender
            };
            let xhr=$.post("RegistrationControllerServlet",data,processresponse);
            xhr.fail(handlerror);
        }
    }
}
function processresponse(responseText,textStatus,xhr){
    let str=responseText.trim();
    if(str==="success")
    {
        swal("Success!","Registration done successfully! You can now login","success");
        setTimeout(redirectUser,3000);
    }
    else if(str==="User already present")
        swal("Error!","Sorry!the userid is already present!","error");
    else
        swal("Error!","Registration failed! Try again","error");
}
function handleError()
{
    swal("Error!","Problem in server communication:"+xhr.statusText,"error");
}
function validateUser()
{
    if(username==="" || password==="" || cpassword==="" || address==="" || city==="" || adhar===""|| email===""|| mobile==="")
    {
        swal("Error!","All fields are mandatory","error");
        return false;
    }
    return true;
}
function checkEmail()
{
    let attheratepos=email.indexOf("@");
    let dotpos=email.indexOf(".");
    if(attheratepos<1 || dotpos<attheratepos+2 || dotpos+2>=email.length)
    {
        swal("Error!","Please enter valid email","error");
        return false;
    }
    return true;
}
function checkMobileNo()
{
    if(mobile.length!=10)
    {
        swal("Error!","Please enter a valid Mobile number","error");
        return false;
    }
    else 
        return true;
}
function validateAdharNo()
{
    var no=/^\d{12}$/;
    if(adhar.match(no))
        return true;
    else
    {
        swal("Error!","Please enter a valid Adhar No.","error");
        return false;
    }
}
function redirectUser()
{
    window.location="login.html";
}

