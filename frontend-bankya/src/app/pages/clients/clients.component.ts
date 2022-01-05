import { Component, OnInit } from '@angular/core';
import { ClientServiceService } from 'src/app/services/client-service.service';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styles: [],
})
export class ClientsComponent implements OnInit {
  public clients: any = [];

  constructor(public clientService: ClientServiceService) {}

  ngOnInit(): void {
    this.clientService.getClients().subscribe((resp) => {
      this.clients = resp;
    });
  }
}
