import { Tag } from './tag.model';
import { User } from './user.model'; 


export interface Comment {
  id?: number;
  content: string;
  tags?: Tag;    
  user?: User;    
  createdAt?: string;
  updatedAt?: string;
}

export interface CommentUpdateDto {
  content: string;
}
