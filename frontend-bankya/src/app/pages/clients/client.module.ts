import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { ClientsComponent } from './list-clients/clients.component';
import { ClientDetailsComponent } from './client-details/client-details.component';
import { AddClientComponent } from './add-client/add-client.component';
import { ReactiveFormsModule } from '@angular/forms';
import { OperationsComponent } from '../operations/operations.component';
import { ValePipe } from 'src/app/shared/pipes/vale.pipe';
import { GreaterPipe } from 'src/app/shared/pipes/greater.pipe';

const routes: Routes = [
  {
    path: '',
    component: ClientsComponent,
    children: [
      { path: '', redirectTo: 'clients', pathMatch: 'full' },
      //{ path: 'clients/add', component: AddClientComponent },
    ],
  },
  { path: 'client/:id/products', component: ClientDetailsComponent },
  { path: 'product/:id/operations', component: OperationsComponent },
];

@NgModule({
  declarations: [
    ClientsComponent,
    ClientDetailsComponent,
    AddClientComponent,
    OperationsComponent,
    ValePipe,
    GreaterPipe
  ],
  imports: [
    CommonModule,
    FontAwesomeModule,
    RouterModule.forChild(routes),
    ReactiveFormsModule,
  ],
})
export class ClientModule {}
