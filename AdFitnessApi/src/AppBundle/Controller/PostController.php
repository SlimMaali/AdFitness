<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Post;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class PostController extends Controller
{
    public function AddAction(Request $request)
    {
        $p = new Post();
        $em = $this->getDoctrine()->getManager();
        $p->setDate("11-01-1996");
        $p->setContent($request->get('Content'));
        $p->setImage($request->get('Image'));
        $p->setTitle($request->get('Title'));
        $em->persist($p);
        $em->flush();
        if(is_null($p))
            return new JsonResponse("false");
        else return new JsonResponse("true");
    }

    public function EditAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $p = $em->getRepository("AppBundle:Post")->find($request->get('id'));


        if(is_null($p))
            return new JsonResponse("false");
        else
        {
            $p->setContent($request->get('Content'));
            $p->setImage($request->get('Image'));
            $p->setTitle($request->get('Title'));
            $em->persist($p);
            $em->flush();
            return new JsonResponse("true");
        }
    }

    public function  RemoveAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $p = $em->getRepository("AppBundle:Post")->find($request->get('id'));


        if(is_null($p))
            return new JsonResponse("false");
        else
        {
            $em->remove($p);
            $em->flush();
            return new JsonResponse("true");
        }
    }

    public function listAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $p = $em->getRepository("AppBundle:Post")->findBy(array(),array('date' => 'DESC'));
        if ($p!=null)
        {
            $serializer=new Serializer([new ObjectNormalizer()]);
            $formated=$serializer->normalize($p);
            return new JsonResponse($formated);
        }
        else return new JsonResponse("Vide");

    }
}
