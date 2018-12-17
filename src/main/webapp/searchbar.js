function searchFunction() 
{
    var i;

    input_title = document.getElementById("myInputForTitle");
    input_startDate = document.getElementById("myInputForStartDate");
    input_endDate = document.getElementById("myInputForEndDate");
    input_location = document.getElementById("myInputForLocation");

    filter_title = input_title.value.toUpperCase();
    filter_startDate = input_startDate.value.toUpperCase();
    filter_endDate = input_endDate.value.toUpperCase();
    filter_location = input_location.value.toUpperCase();

    myCsDiv = document.getElementById("myCsDiv");
    div = myCsDiv.getElementsByClassName("sessions_list");
    
    var sd1 = Date.parse(filter_startDate);
    var ed1 = Date.parse(filter_endDate);
    
    for (i = 0; i < div.length; i++) 
    {
        txtValue_title = document.getElementsByClassName("sessions_list")[i].getAttribute("data-title");
        txtValue_startDate = document.getElementsByClassName("sessions_list")[i].getAttribute("data-startdate");
        txtValue_endDate = document.getElementsByClassName("sessions_list")[i].getAttribute("data-enddate");
        txtValue_location = document.getElementsByClassName("sessions_list")[i].getAttribute("data-location");
        
        var sd2 = Date.parse(txtValue_startDate);
        var ed2 = Date.parse(txtValue_endDate);
    
        if ( (txtValue_title.toUpperCase().indexOf(filter_title) > -1) && (txtValue_location.toUpperCase().indexOf(filter_location) > -1) ) 
        {                
            //si aucune date renseignée            
            if( (isNaN(sd1)) && (isNaN(ed1)) )
            {
                div[i].style.display = "";
            }
            else //si date renseignée
            {
                //si les deux dates sont renseignées
                if( (!isNaN(sd1)) && (!isNaN(ed1)) )
                {
                    //si les deux dates sont comprises dans l'intervalle on affiche
                    if( (sd2 >= sd1) && (ed2 <= ed1) )
                    {
                        div[i].style.display = "";
                    }
                    else //sinon on masque
                    {
                        div[i].style.display = "none";
                    }
                }
                else //si seulement une date est renseignée, on doit tester les deux
                {
                    //si date de début OK
                    if( sd1 == sd2 )
                    {
                        div[i].style.display = "";
                    }
                    else //sinon
                    {
                        //si date de fin OK
                        if( ed1 == ed2 )
                        {
                            div[i].style.display = "";
                        }
                        else //si aucune date n'est OK
                        {
                            div[i].style.display = "none";
                        }
                    }
                }
            }
        } 
        else //si aucun critère OK, on masque
        {
            div[i].style.display = "none";
        }
    }
};

function resetSearchbar()
{
    document.getElementById('myInputForTitle').value = "";
    document.getElementById('myInputForStartDate').value = "";
    document.getElementById('myInputForEndDate').value = "";
    document.getElementById('myInputForLocation').value = "";
    this.searchFunction(); //on effectue une nouvelle recherche vide
}