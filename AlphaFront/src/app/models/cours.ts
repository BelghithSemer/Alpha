import { Document } from "./Document";
import { User } from "./User";

export class cours {
    id:number |undefined;

    title!:string;
    description!:string;
    planification!:string;
    startDate!:Date;
    finishDate!:Date;
    searched!:number;
    
    owner:User|undefined;

   documents : Document[] = []
    //private Set<Document> documents;
}