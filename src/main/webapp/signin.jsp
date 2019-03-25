<div class="modal fade" id="signInModel" tabindex="-1" role="dialog" aria-labelledby="signInModalLabel"
  aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="progress" style="border-bottom-left-radius: 0; border-bottom-right-radius: 0;"
        ng-show="signInForm.loading">
        <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75"
          aria-valuemin="0" aria-valuemax="100" style="width: 75%"></div>
      </div>
      <div class="modal-header">
        <h5 class="modal-title" id="signInModalLabel">Sign In</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form ng-submit="signIn(signInForm.data)">
        <div class="modal-body">
          <div class="response"></div>
          <div class="form-group">
            <label for="signIn-username-or-email">Username/Email</label>
            <input type="text" class="form-control" ng-class="(signInForm.error.usernameOrEmail) ? 'is-invalid' : ''"
              id="signIn-username-or-email" name="username" ng-model="signInForm.data.usernameOrEmail"
              placeholder="Enter username or email" required>
            <span class="text-danger" ng-show="signInForm.error.usernameOrEmail"
              ng-repeat="error in signInForm.error.usernameOrEmail">{{error}}</span>
          </div>
          <div class="form-group">
            <label for="signIn-password">Password</label>
            <input type="password" class="form-control" ng-class="(signInForm.error.password) ? 'is-invalid' : ''"
              id="signIn-password" name="password" ng-model="signInForm.data.password" placeholder="Enter password"
              required>
            <span class="text-danger" ng-show="signInForm.error.password"
              ng-repeat="error in signInForm.error.password">{{error}}</span>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
          <button type="submit" class="btn btn-primary">Sign In</button>
        </div>
      </form>
    </div>
  </div>
</div>