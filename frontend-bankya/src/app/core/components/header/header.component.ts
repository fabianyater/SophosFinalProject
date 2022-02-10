import { UserDto } from 'src/app/security/models/user.dto';
import { GlobalService } from './../../../shared/services/global.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  public isLogged: boolean = false;

  constructor(public router: Router, private globalService: GlobalService) {}

  ngOnInit(): void {}

  public authenticated(user: UserDto) {
    this.globalService.user = user;
    this.isLogged = true;
  }

  signOut() {
    this.globalService.user = {
      username: '',
      password: '',
      jwt: '',
      name: '',
      lastname: '',
    };
    this.isLogged = false;
  }

  goTo(path: string) {
    this.router.navigate([`${path}`]);
  }
}
