<?php
/**
 * Created by IntelliJ IDEA.
 * User: pajdal97
 * Date: 7.4.17
 * Time: 19:15
 */

include './include/header.php';

echo '<div class="container">';
echo '<div class="card-panel" style="height:600px;">';

    echo '<h3>Nastavení oprávnění</h3>
<div class="divider"></div>';

    echo '<div class="row">
    <div class="col s6">
    
    <h4>Dítě 1</h4>
    <div class="input-field col s12">';
$roomsR = $db->query("SELECT * FROM rooms");
while($room = $roomsR->fetch_assoc()) {
    echo '<p>
      <input type="checkbox" id="'.$room['id'].'" />
      <label for="'.$room['id'].'">'.$room['name'].'</label>
    </p>';}
    echo '
  </div>
  
    </div>
    <div class="col s6">
    
        <h4>Dítě 2</h4>
        <div class="input-field col s12">';
$roomsR = $db->query("SELECT * FROM rooms");
while($room = $roomsR->fetch_assoc()) {
    echo '<p>
      <input type="checkbox" id="'.$room['id'].'" />
      <label for="'.$room['id'].'">'.$room['name'].'</label>
    </p>';}
    echo '
  </div>
        
    </div>
</div>';

echo '</div>';

echo '</div>';

echo '<script>
$(document).ready(function() {
    $("select").material_select();
  });
</script>';