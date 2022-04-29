import { Component, OnInit } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { User } from 'src/app/models/user/user';
import { CardService } from 'src/app/services/card/card-service.service';
import { UserService } from 'src/app/services/user.service';
import { lastValueFrom } from 'rxjs';

@Component({
  selector: 'app-card-add-members',
  templateUrl: './card-add-members.component.html',
  styleUrls: ['./card-add-members.component.css']
})
export class CardAddMembersComponent implements OnInit {

  searchedMember: string = '';
  allMembers: User[] = [];
  filteredMembers: User[] = [];
  selectedCard: Card = new Card();

  constructor(private userService: UserService, private cardService: CardService) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  private getAllUsers() {
    this.userService.getUsers();
    this.userService.users().subscribe((users) => {
      this.allMembers = users;
    })
    this.cardService.getCard().subscribe((card) => {
      this.selectedCard = card;
    })
  }

  onKeyUp() {
    if (this.searchedMember !== '') {
      this.filteredMembers = this.allMembers.filter((member) =>
        (!this.selectedCard.assignations
          .some(assignation => assignation.assigneeFirstName === member.firstName && assignation.assigneeLastName === member.lastName)) &&
        (member.firstName.toLocaleLowerCase().startsWith(this.searchedMember.toLocaleLowerCase()) ||
          member.lastName.toLocaleLowerCase().startsWith(this.searchedMember.toLocaleLowerCase())));
    }
    else {
      this.filteredMembers = [];
    }
  }

  async onAssignMember(member: User) {
    const assignation = {
      "userId": member.id,
      "cardId": this.selectedCard.id
    }
    await lastValueFrom(this.cardService.assignUserToCard(assignation)).then(() => {
      this.cardService.readCard(this.selectedCard.id);
      this.searchedMember = '';
    });
  }

}
