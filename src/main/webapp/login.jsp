<div class="modal fade" id="loginModel" tabindex="-1" role="dialog"
  aria-labelledby="loginModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="progress" style="border-bottom-left-radius: 0; border-bottom-right-radius: 0;" ng-show="loading">
        <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75"
          aria-valuemin="0" aria-valuemax="100" style="width: 75%"></div>
      </div>
      <div class="modal-header">
        <h5 class="modal-title" id="loginModalLabel">Login</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form ng-submit="login(user)">
        <div class="modal-body">
          <div class="response"></div>
          <div class="form-group">
            <label for="login-username-or-email">Username/Email</label>
            <input type="text" class="form-control" id="login-username-or-email" ng-model="user.usernameOrEmail"
              placeholder="Enter username or email" required>
          </div>
          <div class="form-group">
            <label for="login-password">Password</label>
            <input type="password" class="form-control" id="login-password" ng-model="user.password"
              placeholder="Password" required>
          </div>
          <div class="form-group form-check">
            <input type="checkbox" class="form-check-input" id="login-remember-me">
            <label class="form-check-label" for="login-remember-me">Remember me</label>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
          <button type="submit" class="btn btn-primary">Login</button>
        </div>
      </form>
    </div>
  </div>
</div>