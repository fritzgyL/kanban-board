import { Tag } from "../tag";

export class Card {
    id: number = 0
    title: string = ""
    deadline: string = ""
    estimatedTime: number = 0
    location: string = ""
    url: string = ""
    description: string = ""
    tags: Tag[] = []
}
