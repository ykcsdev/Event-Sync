import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { EventService, Event } from '../../services/event.service';

@Component({
  selector: 'app-event-add',
  imports: [FormsModule, CommonModule],
  templateUrl: './event-add.html',
  styleUrl: './event-add.scss',
})
export class EventAdd {
  event: Event = { title: '', description: '' };
  loading = false;
  error: string | null = null;
  success = false;

  constructor(
    private eventService: EventService,
    private router: Router
  ) {}

  save() {
    // Validation
    if (!this.event.title.trim() || !this.event.description?.trim()) {
      this.error = 'Title and description are required';
      return;
    }

    this.loading = true;
    this.error = null;
    this.eventService.createEvent(this.event).subscribe({
      next: () => {
        this.success = true;
        this.loading = false;
        setTimeout(() => {
          this.router.navigate(['/events']);
        }, 1000);
      },
      error: (err) => {
        this.error = 'Failed to create event. Please try again.';
        this.loading = false;
        console.error('Error creating event:', err);
      },
    });
  }

  cancel() {
    this.router.navigate(['/events']);
  }
}
