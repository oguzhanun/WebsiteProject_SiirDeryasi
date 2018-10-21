<html>

    <head>    
        <title>Jquery</title>   
     <script src="https://code.jquery.com/jquery-3.2.1.js"></script>

    </head>

    <body>
        
        <p>Sıfırdan Web Developer Eğitimi</p>      
     
        <button id="hide">Hide</button>
        <button id="show">Show</button>
        
         <button id="toggle">Toggle</button>
        
        <script> 
          
            $(document).ready(function(){
                
                
               
                $("#hide").click(function(){
                    
                    $("p").hide(3000);
                    
                });
                
                $("#show").click(function(){
                    
                    $("p").show(1000,function(){
                        alert("paragraf gösterildi");
                    });
                    
                });
                
                $("#toggle").click(function(){
                    
                    $("p").toggle("slow");
                    
                });
                
                
                
            });
            
            
        </script>
        
    </body>

</html>