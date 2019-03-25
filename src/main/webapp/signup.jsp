<div class="modal fade" id="signUpModel" tabindex="-1" role="dialog" aria-labelledby="signUpModalLabel"
    aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="progress" style="border-bottom-left-radius: 0; border-bottom-right-radius: 0;"
                ng-show="signUpForm.loading">
                <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar"
                    aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 75%"></div>
            </div>
            <div class="modal-header">
                <h5 class="modal-title" id="signUpModalLabel">Create Account</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form ng-submit="signUp(signUpForm.data)">
                <div class="modal-body">
                    <div class="response"></div>
                    <div class="form-group">
                        <label for="signUp-username">Username</label>
                        <input type="text" class="form-control" ng-class="(signUpForm.error.username) ? 'is-invalid' : ''" id="signUp-username" ng-model="signUpForm.data.username"
                            placeholder="Enter username" required>
                        <span class="text-danger" ng-show="signUpForm.error.username"
                            ng-repeat="error in signUpForm.error.username">{{error}}</span>
                    </div>
                    <div class="form-group">
                        <label for="signUp-password">Password</label>
                        <input type="password" class="form-control" ng-class="(signUpForm.error.password) ? 'is-invalid' : ''" id="signUp-password" ng-model="signUpForm.data.password"
                            placeholder="Enter password" required>
                        <span class="text-danger" ng-show="signUpForm.error.password"
                            ng-repeat="error in signUpForm.error.password">{{error}}</span>
                    </div>
                    <div class="form-group">
                        <label for="signUp-confirmPassword">Repeat Password</label>
                        <input type="password" class="form-control" ng-class="(signUpForm.error.confirmPassword) ? 'is-invalid' : ''" id="signUp-confirmPassword"
                            ng-model="signUpForm.data.confirmPassword" placeholder="Enter password again" required>
                        <span class="text-danger" ng-show="signUpForm.error.confirmPassword"
                            ng-repeat="error in signUpForm.error.confirmPassword">{{error}}</span>
                    </div>
                    <div class="form-group">
                        <label for="signUp-email">Email Address</label>
                        <input type="email" class="form-control" ng-class="(signUpForm.error.email) ? 'is-invalid' : ''" id="signUp-email" ng-model="signUpForm.data.email"
                            placeholder="Enter email" required>
                        <span class="text-danger" ng-show="signUpForm.error.email"
                            ng-repeat="error in signUpForm.error.email">{{error}}</span>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Sign Up</button>
                </div>
            </form>
        </div>
    </div>
</div>