import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { BoardComponent } from './components/board/board.component';
const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: '', component: DashboardComponent, },
  { path: 'board/:id', component: BoardComponent, }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }