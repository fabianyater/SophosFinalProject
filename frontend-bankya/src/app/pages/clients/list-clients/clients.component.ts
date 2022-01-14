import { Component, OnInit } from '@angular/core';
import { ClientModel } from 'src/app/models/client-model';
import { Router } from '@angular/router';
import { ClientServiceService } from 'src/app/services/client-service.service';
@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css'],
})
export class ClientsComponent implements OnInit {
  public clients: Array<ClientModel> = [];
  public errorMessage!: string;
  public error: boolean = false;

  constructor(
    public clientService: ClientServiceService,
    public router: Router
  ) {}

  ngOnInit(): void {
    this.clientService.getClients().subscribe((resp: any) => {
      this.clients = resp;
    });
  }

  goTo(id: number) {
    this.router.navigate([`/client/${id}/products`]);
  }
}
