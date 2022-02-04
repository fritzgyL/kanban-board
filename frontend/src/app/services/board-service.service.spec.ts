import { TestBed } from '@angular/core/testing';

import { BoardServiceService } from './board-service.service';

describe('BoardServiceService', () => {
  let service: BoardServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BoardServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
