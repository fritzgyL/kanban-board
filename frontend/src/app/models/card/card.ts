import { Assignation } from "../assignation/assignation";
import { Tag } from "../tag/tag";

export class Card {
    id: number = 0
    title: string = ""
    deadline: string | null = ""
    estimatedTime: number = 0
    location: string = ""
    url: string = ""
    description: string = ""
    position: number = 0
    tags: Tag[] = []
    assignations: Assignation[] = [];
}
