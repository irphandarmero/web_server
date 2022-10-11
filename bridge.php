<?php 
    $target ="";
    $method ="";
    if ($argv !== null){
        //print_r($argv);
        foreach( $argv as $argument){
            if ($argument == $argv[0] ) continue;

            $pair = explode("=", $argument);
            $variableName = substr($pair[0],2);
            $variableValue = $pair[1];
            if($variableName == "target"){
                $target = $variableValue;
            } else if($variableName == "method"){
                $method = $variableValue;
            } else {
                if ($method == "GET"){
                    $_GET[$variableName] = $variableValue;
                } else if ($method == "POST"){
                    $_POST[$variableName] = $variableValue;
                }
            }
        }
    }
    //echo $target;
    require_once($target);
?>