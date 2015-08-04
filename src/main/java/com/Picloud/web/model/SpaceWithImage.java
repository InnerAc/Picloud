package com.Picloud.web.model;

import java.util.List;

import com.Picloud.hibernate.entities.Space;

public class SpaceWithImage {
        
        private Space space;
        
        private List<Image> images;
        public SpaceWithImage() {
                super();
                // TODO Auto-generated constructor stub
        }
        public SpaceWithImage(Space space, List<Image> images) {
                super();
                this.space = space;
                this.images = images;
        }
        public Space getSpace() {
                return space;
        }
        public void setSpace(Space space) {
                this.space = space;
        }
        public List<Image> getImages() {
                return images;
        }
        public void setImages(List<Image> images) {
                this.images = images;
        }
}
