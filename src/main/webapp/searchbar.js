/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function myNewFunction() 
{
    var i;

    input_title = document.getElementById("myInputForTitle");
    input_date = document.getElementById("myInputForDate");
    input_location = document.getElementById("myInputForLocation");

    filter_title = input_title.value.toUpperCase();
    filter_date = input_date.value.toUpperCase();
    filter_location = input_location.value.toUpperCase();

    myCsDiv = document.getElementById("myCsDiv");
    div = myCsDiv.getElementsByClassName("sessions_list");

    for (i = 0; i < div.length; i++) 
    {
        txtValue_title = document.getElementsByClassName("sessions_list")[i].getAttribute("data-title");
        txtValue_date = document.getElementsByClassName("sessions_list")[i].getAttribute("data-startdate");
        txtValue_location = document.getElementsByClassName("sessions_list")[i].getAttribute("data-location");

        console.log('txtValue_title = '+ txtValue_title);
        console.log('txtValue_date = '+ txtValue_date);
        console.log('txtValue_location = '+ txtValue_location);

        if ( (txtValue_title.toUpperCase().indexOf(filter_title) > -1) && (txtValue_date.toUpperCase().indexOf(filter_date) > -1) && (txtValue_location.toUpperCase().indexOf(filter_location) > -1) ) 
        {
            div[i].style.display = "";
        } else {
            div[i].style.display = "none";
        }
    }
}