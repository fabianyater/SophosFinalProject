import { Router } from '@angular/router';
import { AuthService } from 'src/app/shared/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { UserModel } from 'src/app/users/models/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  username: string | undefined;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    if (this.authService.getToken()) {
      this.isLoggedIn = true;
    }
  }

  onSubmit() {
    this.authService.login(this.form).subscribe(
      (resp) => {
        let user: UserModel;

        this.authService.saveToken(resp.token!);
        this.authService.saveUser(resp.data!);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        user = JSON.parse(this.authService.getUser()!);
        this.username = user.username;
        this.router.navigateByUrl('/customers');
      },
      (error) => {
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }
}
