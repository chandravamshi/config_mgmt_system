import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { Config } from '../interfaces/config';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
@Component({
  selector: 'app-edit-config-dialog',
  standalone: true,
  imports: [CommonModule, FormsModule, MatInputModule, MatFormFieldModule,MatDialogModule,MatButtonModule],
  templateUrl: './edit-config-dialog.component.html',
  styleUrl: './edit-config-dialog.component.css',
})
export class EditConfigDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<EditConfigDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Config
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
