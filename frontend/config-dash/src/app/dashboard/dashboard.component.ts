import { Component } from '@angular/core';
import { ConfigListComponent } from '../config-list/config-list.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [ConfigListComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
