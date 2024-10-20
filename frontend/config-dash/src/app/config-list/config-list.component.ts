import { Component, OnInit, ViewChild } from '@angular/core';
import { Config } from '../interfaces/config';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';

import { MatDialog } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { ConfigService } from '../config.service';
import { EditConfigDialogComponent } from '../edit-config-dialog/edit-config-dialog.component';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
@Component({
  selector: 'app-config-list',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatSort,
    MatPaginator,
  ],
  templateUrl: './config-list.component.html',
  styleUrl: './config-list.component.css',
})
export class ConfigListComponent implements OnInit {
  displayedColumns: string[] = [
    'id',
    'configKey',
    'value',
    'description',
    'actions',
  ];
  dataSource: Config[] =[];
  //dataSource: MatTableDataSource<Config>;

  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private configService: ConfigService, public dialog: MatDialog) {
    /* const ELEMENT_DATA: Config[] = [
      {
        id: 1,
        configKey: 'API_URL',
        value: 'https://api.example.com',
        description: 'API base URL',
      },
      {
        id: 2,
        configKey: 'MAX_USERS',
        value: '100',
        description: 'Maximum number of users',
      },
      {
        id: 3,
        configKey: 'DEBUG_MODE',
        value: 'true',
        description: 'Enable debug mode',
      },
    ];
    this.dataSource = new MatTableDataSource<Config>(ELEMENT_DATA); */
  }

  ngOnInit(): void {
    this.fetchConfigs();
  }

  fetchConfigs(): void {
    this.configService.getConfigs().subscribe((configs) => {
      this.dataSource = configs;
      
    });
  }

  editConfig(config: Config): void {
    const dialogRef = this.dialog.open(EditConfigDialogComponent, {
      width: '900px',
      data: config, // Pass the selected config to the dialog
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        // Call update method in your service
        /* this.configService.updateConfig(config.id, result).subscribe(() => {
          this.fetchConfigs(); // Refresh the list after updating
        }); */

        if (config.id !== undefined) {
          // Check if id is defined
          this.configService.updateConfig(config.id, result).subscribe(() => {
            this.fetchConfigs(); // Refresh the list after updating
          });
        } else {
          console.error('Config ID is undefined'); // Handle error case
        }
      }
    });
  }

  deleteConfig(id: number): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '300px', // Set dialog width
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // If confirmed, call the delete method in your service
        this.configService.deleteConfig(id).subscribe(() => {
          this.fetchConfigs(); // Refresh the list after deletion
        });
      }
    });
  }

  addConfig(): void {
    const newConfig: Config = { configKey: '', value: '', description: '' }; // Create an empty config object

    const dialogRef = this.dialog.open(EditConfigDialogComponent, {
      width: '600px',
      height: '400px',
      data: newConfig, // Pass the empty config to the dialog
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        // Call create method in your service
        this.configService.createConfig(result).subscribe(() => {
          this.fetchConfigs(); // Refresh the list after adding
        });
      }
    });
  }
}
