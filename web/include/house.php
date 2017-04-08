<?php
/**
 * Created by IntelliJ IDEA.
 * User: pajdal97
 * Date: 7.4.17
 * Time: 19:52
 */
echo '<style>
.room {
    cursor:pointer;
    position:absolute;
    background:rgba(0,0,0,1);
    opacity:0.2;
    box-shadow: 0 6px 10px 0 rgba(0, 0, 0, 0.14), 0 1px 18px 0 rgba(0, 0, 0, 0.12), 0 3px 5px -1px rgba(0, 0, 0, 0.3);
    transition:all 0.3s;
}
.room:hover {
    box-shadow: 0 16px 24px 2px rgba(0, 0, 0, 0.14), 0 6px 30px 5px rgba(0, 0, 0, 0.12), 0 8px 10px -5px rgba(0, 0, 0, 0.3);
    opacity:0.5;
}
.light {
    position:absolute;
    width:30px;
    height:30px;
    border-radius:100%;
    transition:all .3s;
    background:gray;
    box-shadow: 0px 0px 5px 0px rgba(0,0,0,0.5);
    cursor:pointer;
}
.light:hover {
    border:1px solid black;
}
.light[light="on"] {
    background:white;
    box-shadow: 0px 0px 90px 20px yellow;
}
.door {
    cursor:pointer;
    position: absolute;
    background: rgba(255,0,0,0.62);
    top: 200px;
    left: 395px;
    width: 20px;
    height: 70px;
    border-top: 3px solid black;
    border-bottom: 3px solid black;
    transition:all .3s;
}
.door[door="on"] {
    background: rgba(0,0,0,0.29);
}
.door > .door_id {
    position:absolute;
    left:7px;
    top:0px;
    height:64px;
    width:6px;
    background:#282828;
    transition:all .3s;
}
.door[door="on"] > .door_id {
    background:rgba(40,40,40,0.5);
    top:-67px;
}

</style>';
echo '<div id="house" style="position:relative;height:100px;">';

    $roomsR = $db->query("SELECT * FROM rooms");
    while($room = $roomsR->fetch_assoc()) {
        $objectsR = $db->query("SELECT * FROM objects WHERE room='".$room['id']."'");
        $attr_object = "";
        while($object = $objectsR->fetch_assoc()) {
            $attr_object .= " ".$object['type']."='".$object['data']."'";
        }
        echo '<div class="room" room_id="'.$room['id'].'" name="'.$room['name'].'" '.$attr_object.' style="background:'.$room['color'].';top:'.$room['top'].'px;left:'.$room['left'].'px;width:'.$room['width'].'px;height:'.$room['height'].'px;"></div>';
    }

    echo '<div class="door" door_id="6" style="top:150px;"><div class="door_id"></div></div>';
    echo '<div class="door" door_id="7" style="top:320px;"><div class="door_id"></div></div>';
    echo '<div class="door" door_id="8" style="top:300px;left:505px;"><div class="door_id"></div></div>';
    echo '<div class="door" door_id="9" style="top:300px;left:900px;"><div class="door_id"></div></div>';

echo '<script>
var lights = [];
function reload_objects() {
    
    // Lights
    
    $.ajax({
        method: "GET",
        url: "http://10.10.4.127:8080/api/get",
        data: { token:"easy_token", type:"light", find_by:"type", t: Date.now },
        success: function(data) {     
            lights = data;
            for(i=0;i<lights.data.length;i++) {
                $(".light[room_id=\'"+lights.data[i].room+"\']").attr("light",lights.data[i].data);
                $(".light[room_id=\'"+lights.data[i].room+"\']").attr("obj_id",lights.data[i].id);
            }
            setTimeout(function(){reload_objects()},500);
        }
    });
}
reload_objects();

var doors = [];
function reload_doors() {
    
    // Doors
    
    $.ajax({
        method: "GET",
        url: "http://10.10.4.127:8080/api/get",
        data: { token:"easy_token", type:"door", find_by:"type", t: Date.now },
        success: function(data) {     
            doors = data;
            for(i=0;i<doors.data.length;i++) {
                $(".door[door_id=\'"+doors.data[i].id+"\']").attr("door",doors.data[i].data); 
            }
            setTimeout(function(){reload_doors()},500);
        }
    });
}
setTimeout(function(){reload_doors();},250);
$(document).ready(function(){
    var position,width,height;
    var light = [];
    $(".room[light]").each(function( index ) {
       position = $(this).position();
       width = $(this).width();
       height = $(this).height();
       light.left = (position.left+((width/2))-15);
       light.top = (position.top+((height/2))-15);
       $("#house").append(\'<div class="light" light="\'+($(this).attr("light"))+\'" name="\'+($(this).attr("name"))+\'" room_id="\'+($(this).attr("room_id"))+\'" style="top:\'+(light.top)+\'px;left:\'+(light.left)+\'px;"></div>\');
       
    });

var light_set;
$(".light").click(function(){
    if($(this).attr("light") == "on") light_set = "off";
    else light_set = "on";
    $.ajax({
        method: "GET",
        url: "http://10.10.4.127:8080/api/action",
        data: { token:"easy_token", type:"light", status:light_set, find_by:"id", find_v:$(this).attr("obj_id"), t: Date.now },
        success: function(data) {     

        }
    });
    $.ajax({
        method: "GET",
        url: "http://192.168.1.238:5000/api/action",
        data: { id:$(this).attr("obj_id"), state:light_set ,t: Date.now },
        success: function(data) {     

        }
    });
});

var door_set;
$(".door").click(function(){
    if($(this).attr("door") == "on") door_set = "off";
    else door_set = "on";
    $.ajax({
        method: "GET",
        url: "http://10.10.4.127:8080/api/action",
        data: { token:"easy_token", type:"door", status:door_set, find_by:"id", find_v:$(this).attr("door_id"), t: Date.now },
        success: function(data) {     

        }
    });
    $.ajax({
        method: "GET",
        url: "http://192.168.1.238:5000/api/action",
        data: { id:$(this).attr("obj_id"), state:light_set ,t: Date.now },
        success: function(data) {     

        }
    });
    
});


});
</script>';

echo '</div>';
