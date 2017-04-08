<?php
/**
 * Created by IntelliJ IDEA.
 * User: pajdal97
 * Date: 7.4.17
 * Time: 19:15
 */

include './include/header.php';

echo '<div class="container">';
    echo '<div class="row">
    <form class="col s6 offset-s3">
      <div class="card-panel z-depth-3" style="min-height:300px;margin-top:20px;">
      <h4>Přihlásit se</h4>
      <div class="row">
        <div class="input-field col s12">
          <input id="username" type="text" class="validate">
          <label for="username">Username</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <input id="password" type="password" class="validate">
          <label for="password">Password</label>
        </div>
      </div>
      <div class="row">
        <div class="col s12">
            <button class="btn waves-effect waves-light blue" type="submit" name="action">Přihlásit
                <i class="material-icons right">send</i>
            </button>
        </div>
      </div>
      </div>
    </form>
  </div>';
echo '</div>';

echo '</div>';