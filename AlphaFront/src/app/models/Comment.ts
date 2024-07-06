import { Book } from "./Book";
import { User } from "./User";

export class Comment{
   
    id!:number;

    content!:string;

    rate!:number;

    date!:Date;

    
    user: User|undefined;


    
    book: Book | undefined;
}