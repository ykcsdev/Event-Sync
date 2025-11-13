import { Routes } from '@angular/router';
import { EventList } from './components/event-list/event-list';
import { EventAdd } from './components/event-add/event-add';
import { EventSummary } from './components/event-summary/event-summary';
import { Feedback } from './components/feedback/feedback';

export const routes: Routes = [
  { path: '', redirectTo: 'events', pathMatch: 'full' },
  { path: 'events', component: EventList },
  { path: 'events/add', component: EventAdd },
  { path: 'events/:eventId/summary', component: EventSummary,data: { renderMode: 'client' } },
  { path: 'events/:eventId/feedback', component: Feedback,data: { renderMode: 'client' } },
];
