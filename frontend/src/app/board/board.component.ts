import { Component, OnInit } from '@angular/core';
import { Board } from '../class/board';
@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  board: Board = new Board()

  constructor() {
  }

  ngOnInit(): void {

  }



}
