import { UserModel } from 'src/app/users/models/user.model';
import { AuthService } from 'src/app/shared/services/auth.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  constructor(public router: Router, private authService: AuthService) {}

  public isLogged: boolean = false;
  public username: string = '';

  ngOnInit(): void {
    this.isLogged = !!this.authService.getToken();

    if (this.isLogged) {
      let user: UserModel;
      user = JSON.parse(this.authService.getUser()!);
      this.username = user.username;
    } else {
      this.isLogged = false;
    }
  }

  public logout() {
    this.authService.logOut();
    this.isLogged = false;
  }

  goTo(path: string) {
    this.router.navigate([`${path}`]);
  }
}
